import {TaskList} from "../models/TaskList";

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
    public getTaskLists(): TaskList[] {
        const headers = this.getAuthConfig();
        let taskLists: Array<TaskList> = [];
        fetch(`${this.API_URL}/taskLists/`, {
            method: 'GET', headers
        }).then(response => {
            response.json().then(data => {
                for (let i = 0; i < data.length; i++) {
                    let taskList = new TaskList(data[i].id, data[i].name, data[i].tasks, data[i].empty, data[i].completed)
                    console.log(`taskList` + JSON.stringify(taskList))
                    taskLists.push(taskList)
                }
            }).catch(error => {
                console.log(error)
            });
        })
        return taskLists;
    }

    public async login(username: String, password: String): Promise<boolean> {
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
            console.log(error)
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
    public register(
        username: String,
        password: String,
        email: String,
        firstName: String,
        lastName: String
    ): boolean {
        fetch(`${this.API_URL}/auth/register`, {
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
            }
        ).then(response => {
            response.json().then(data => {
                localStorage.setItem("token", data.jwtToken);
                return true;
            })
        })
            .catch(error => {
                console.log(error)
            });
        return false;
    }

    public static pushTasks(taskListId: number, tasks: any) {
        let data = {
            "tasks": []
        }
        console.log(tasks)
    }

    public setTaskIsCompleted(taskListId: number, taskId: number, isCompleted: boolean) {
        const headers = this.getAuthConfig();
        fetch(`${this.API_URL}/tasks/${taskId}?` + isCompleted, {
                method: 'PUT',
                headers
            }
        )
            .then(response => {})
            .catch(error => {
                console.log(error)
            })
    }

    public deleteTasklist(taskListId: number) {
        const headers = this.getAuthConfig();
        fetch(`${this.API_URL}/taskLists/${taskListId}`, {
                method: 'DELETE',
                headers
            }
        )
            .then(response => {})
            .catch(error => {
                console.log(error)
            })
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