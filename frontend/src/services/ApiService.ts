import axios from "axios";

class ApiService {
    private static API_URL: String = "http://localhost:8080/api";

    public static async getTaskLists() {
        return await axios
            .get(
                `${this.API_URL}/taskLists/`, this.getAuthConfig()
            )
    }

    public static async login(username: String, password: String): Promise<Boolean> {
        return axios
            .post(
                `${this.API_URL}/auth/login`, {
                    username: username,
                    password: password
                },
                {
                    headers: {
                        'Content-Type': 'application/json'
                    }
                }
            )
            .then(response => {
                    localStorage.setItem("token", response.data.jwtToken);
                    return true;
                }
            )
            .catch(error => {
                return false;
            });
    }

    public static logout() {
        localStorage.removeItem("token");
    }

    public static async register(
        username: String,
        password: String,
        email: String,
        firstName: String,
        lastName: String
    ) {
        return axios
            .post(
                `${this.API_URL}/auth/register`, {
                    username: username,
                    password: password,
                    email: email,
                    firstName: firstName,
                    lastName: lastName
                },
                {
                    headers: {
                        'Content-Type': 'application/json'
                    }
                }
            )
            .then(response => {
                    localStorage.setItem("token", response.data.jwtToken);
                    console.log(response.data.jwtToken)
                    return true;
                }
            )
            .catch(error => {
                return false;
            });
    }

    public static pushTasks(taskListId: number, tasks: any) {
        let data = {
            "tasks": []
        }
        console.log(tasks)
    }

    public static setTaskIsCompleted(taskListId: number, taskId: number, isCompleted: boolean) {
        axios//localhost:8080/api/tasks/{{taskId}}?completed=true
            .put(
                `${this.API_URL}/tasks/${taskId}?completed=${isCompleted}`, {}, this.getAuthConfig()
            )
            .then(response => {

            })
            .catch(error => {
                console.log(error)
            });
    }

    public static deleteTasklist(taskListId: number) {
        axios
            .delete(
                `${this.API_URL}/taskLists/${taskListId}`, this.getAuthConfig()
            )
            .then(response => {

            })
            .catch(error => {
                console.log(error)
            });
    }


    private static getToken(): String {
        return localStorage.getItem("token");
    }

    private static getAuthConfig() {
        return {
            headers: {Authorization: `Bearer ${this.getToken()}`}
        }
    }

    static updateTasklistName(id: number, name) {
        axios
            .put(`${this.API_URL}/taskLists/${id}`, {name: name}, this.getAuthConfig())
            .then(response => {

                }
            )
            .catch(error => {
                console.log(error)
            })
    }
}

export default ApiService;