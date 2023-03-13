import {TaskList} from "../models/TaskList";

class ApiService {
    private readonly API_URL: string = "http://localhost:8080/api";

    private static instance: ApiService;

    private constructor() {}

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
        const headers = this.getAuthConfig();
        const taskLists: Array<TaskList> = [];
        let data
        try {
            data = await fetch(`${this.API_URL}/taskLists/`, {
                method: 'GET', headers
            }).then(response => {
                if(response.status !== 498) {
                    response.json();
                }
            });
            if(!data) return null;
            data.forEach(taskList => {
                taskLists.push(
                    new TaskList(taskList.id, taskList.name, taskList.tasks, taskList.empty, taskList.completed)
                )
            })

        } catch (error) {
            console.log(error)
        }
        return taskLists;
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
            }).then(response => response.json());
            console.log(data.jwtToken)
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
     * @param firstName
     * @param lastName
     */
    public async register(
        username: string,
        password: string,
        email: string,
        firstName: string,
        lastName: string
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
                    email: email,
                    firstName: firstName,
                    lastName: lastName
                })
            }).then(response => response.json());
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