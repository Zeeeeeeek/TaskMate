import { Task } from './Task';

class TaskList {
    id: string;
    name: string;

    tasks: Task[];

    empty: boolean;

    completed: boolean;
}