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
        return await fetch(queryUrl, {
            method,
            headers,
            body: body ? JSON.stringify(body) : null
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

    private getContentTypeJson(): Headers {
        const headers = new Headers();
        headers.append('Content-Type', 'application/json')
        return headers;
    }

    public async updateTasklistName(id: number, name) {
        const headers = this.getAuthConfig();
        await fetch(`${this.API_URL}/task-lists/${id}`, {
            method: 'PUT',
            headers,
            body: name
        }).then(response => {
            if (response.status === 498) {
                this.logout();
                window.location.reload();
            }
        })
            .catch(error => {
                console.log(error)
            })
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
        const headers = this.getAuthConfig();
        this.setContentTypeToJson(headers);
        const data = {
            "tasks": [task]
        }
        return this.apiCall(`task-lists/${taskListID}/tasks`, 'POST', data, headers)
            .then(data => {
                const task = data[0];
                if (task["@type"] === "simpleTask") {
                    return new SimpleTask(
                        task.id,
                        task.name,
                        task.description,
                        task.completed
                    )
                } else {
                    return new TimeConstraintTask(
                        task.id,
                        task.name,
                        task.description,
                        task.completed,
                        task.dueDate,
                        task.expired
                    )
                }
            });
    }

    public async setTaskIsCompleted(taskID: string, completed: boolean) {
        await this.apiCall(`tasks/${taskID}?completed=${completed}`, 'PUT');
    }
}

export default ApiService.getInstance();