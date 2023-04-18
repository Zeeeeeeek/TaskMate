import { Task } from './Task';

export class TaskListModel {
    id: string;
    name: string;

    tasks: Task[];

    empty: boolean;

    completed: boolean;
    constructor(id: string, name: string, tasks: Task[], empty: boolean, completed: boolean) {
        this.id = id;
        this.name = name;
        this.tasks = tasks;
        this.empty = empty;
        this.completed = completed;
    }
}