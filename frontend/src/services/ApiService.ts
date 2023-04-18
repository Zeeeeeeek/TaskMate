import {TaskListModel} from "../models/TaskListModel";
import ResponseError from "../errors/ResponseError";
import {SimpleTask, TimeConstraintTask} from "../models/Task";

class ApiService {
    private readonly API_URL: string = "http://localhost:8080/api";

    private static instance: ApiService;

    private constructor() {
    }

    public static getInstance(): ApiService {
        if (this.instance === undefined) {
            this.instance = new ApiService();
        }
        return this.instance;
    }

    /**
     * Get all tasklists
     * @returns {TaskListModel[]} Array of TaskList objects
     */
    public async getTaskLists(): Promise<TaskListModel[]> {
        const data = await this.apiCall('task-lists', 'GET');
        return data.map(
            (taskList: any) => new TaskListModel(
                taskList.id,
                taskList.name,
                taskList.tasks,
                taskList.empty,
                taskList.completed)
        );
    }

    public async apiCall(url: string, method: string, body: any = null, headers: Headers = this.getAuthConfig(), params: any = null): Promise<any> {
        const response = await this.rawApiCall(
            url,
            method,
            body,
            headers,
            params
        );
        if (response.ok) {
            return response.headers.get('Content-Length') === '0' ? null : response.json()
        } else {
            if (response.status === 498) this.refreshToken();
            throw new ResponseError(response.status);
        }
    }

    public async rawApiCall(url: string, method: string, body: any, headers: Headers, params: any): Promise<Response> {
        const queryUrl = !params ? `${this.API_URL}/${url}` : `${this.API_URL}${url}?${Object.keys(params).map(key => `${key}=${params[key]}`).join('&')}`;
        if (headers.get('Content-Type') === 'application/json') {
            body = JSON.stringify(body);
        }
        return await fetch(queryUrl, {
            method,
            headers,
            body: body
        });
    }


    private refreshToken() {
        this.logout();//todo: implement refresh token
        window.location.reload();
    }

    public async login(username: string, password: string): Promise<boolean> {
        const response = await this.apiCall('auth/login', 'POST', {
            username: username,
            password: password
        }, this.getContentTypeJson())
        if (response) {
            localStorage.setItem("token", response.jwtToken);
            return true;
        }
        return false;
    }

    public logout() {
        localStorage.removeItem("token");
    }

    /**
     * Registers a new user and saves the token in local storage
     * @param username
     * @param password
     * @param email
     */
    public async register(
        username: string,
        password: string,
        email: string,
    ): Promise<boolean> {
        const response = await this.apiCall('auth/register', 'POST', {
            username: username,
            password: password,
            email: email
        }, this.getContentTypeJson())
        if (response) {
            localStorage.setItem("token", response.jwtToken);
            return true;
        }
        return false;
    }

    private getToken(): String {
        return localStorage.getItem("token");
    }

    private getAuthConfig(): Headers {
        const headers = new Headers();
        headers.append('Authorization', `Bearer ${this.getToken()}`)
        return headers;
    }

    private setContentTypeToJson(headers: Headers): HeadersInit {
        headers.append('Content-Type', 'application/json')
        return headers;
    }

    private getAuthAndContentTypeJson(): Headers {
        const headers = this.getAuthConfig();
        this.setContentTypeToJson(headers)
        return headers;
    }

    private setConteTypeToText(headers: Headers): HeadersInit {
        headers.append('Content-Type', 'text/plain')
        return headers;
    }

    private getAuthAndContentTypeText(): Headers {
        const headers = this.getAuthConfig();
        this.setConteTypeToText(headers)
        return headers;
    }

    private getContentTypeJson(): Headers {
        const headers = new Headers();
        headers.append('Content-Type', 'application/json')
        return headers;
    }

    public async updateTasklistName(id: number, name) {
        await this.apiCall(`task-lists/${id}`, 'PUT', name, this.getAuthAndContentTypeText());
    }

    public isLoggedIn() {
        return this.getToken() !== null;
    }

    public async addTaskList(): Promise<TaskListModel> {
        const body = {
            "name": "",
            "tasksDTO": {
                "tasks": []
            }
        }
        const headers = this.getAuthAndContentTypeJson();
        const data = await this.apiCall('task-lists', 'POST', body, headers);
        return new TaskListModel(
            data.id,
            data.name,
            data.tasks,
            data.empty,
            data.completed
        );
    }

    public async addTask(taskListID: string, task) {
        const headers = this.getAuthAndContentTypeJson();
        const body = {
            "tasks": [task]
        }
        const response = await this.apiCall(`task-lists/${taskListID}/tasks`, 'POST', body, headers);
        const createdTask = response[0];
        return createdTask["@type"] === "simpleTask" ? new SimpleTask(
            createdTask.id,
            createdTask.name,
            createdTask.description,
            createdTask.completed
        ) : new TimeConstraintTask(
            createdTask.id,
            createdTask.name,
            createdTask.description,
            createdTask.completed,
            createdTask.dueDate,
            createdTask.expired
        );
    }

    public async setTaskIsCompleted(taskID: string, completed: boolean) {
        await this.apiCall(`tasks/${taskID}?completed=${completed}`, 'PUT');
    }
}

export default ApiService.getInstance();