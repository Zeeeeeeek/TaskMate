import {TaskList} from "../models/TaskList";
import ResponseError from "../errors/ResponseError";

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
     * @returns {TaskList[]} Array of TaskList objects
     */
    public async getTaskLists(): Promise<TaskList[]> {
            return this.apiCall('task-lists', 'GET', null).then(data => {
                return data.map(
                    (taskList: any) => new TaskList(
                        taskList.id,
                        taskList.name,
                        taskList.tasks,
                        taskList.empty,
                        taskList.completed)
                );
            });
    }

    public async apiCall(url: string, method: string, body: any = null): Promise<any> {
        const headers = this.getAuthConfig();
        console.log(`URL: ${this.API_URL}/${url}`)
        return await fetch(`${this.API_URL}/${url}`, {
            method,
            headers,
            body: body ? JSON.stringify(body) : null
        }).then(response => {
            if (response.ok) {
                return response.json();
            } else {
                if (response.status === 498) {
                    this.logout();
                    window.location.reload();
                }
                throw new ResponseError(response.status);
            }
        });
    }

    public async login(username: string, password: string): Promise<boolean> {
        try {
            const data = await fetch(`${this.API_URL}/auth/login`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    username: username,
                    password: password
                })
            }).then(response => {
                if (response.status !== 200) return '';
                return response.json();
            })
            if (!data || !data.jwtToken) return false;
            localStorage.setItem("token", data.jwtToken);
            return true;
        } catch (error) {
            const mute = error;
            return false;
        }
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
        try {
            const data = await fetch(`${this.API_URL}/auth/register`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    username: username,
                    password: password,
                    email: email
                })
            }).then(response => {
                if (response.status !== 200) return '';
                return response.json();
            });
            if (!data || !data.jwtToken) return false;
            localStorage.setItem("token", data.jwtToken);
            return true;
        } catch (error) {
            console.log(error)
            return false;
        }
    }

    public async verifyToken(): Promise<boolean> {
        try {
            const responseStatus = await fetch(`${this.API_URL}/auth/verify`, {
                method: 'GET',
                headers: this.getAuthConfig()
            }).then(response => response.status);
            return responseStatus !== 498;
        } catch (error) {
            console.log(error)
            return false;
        }
    }

    private getToken(): String {
        return localStorage.getItem("token");
    }

    private getAuthConfig(): HeadersInit {
        const headers = new Headers();
        headers.append('Authorization', `Bearer ${this.getToken()}`)
        return headers;
    }

    public updateTasklistName(id: number, name) {
        const headers = this.getAuthConfig();
        fetch(`${this.API_URL}/taskLists/${id}`, {
            method: 'PUT',
            headers,
            body: name
        }).catch(error => {
            console.log(error)
        })
    }

}

export default ApiService.getInstance();