type TaskType = "simpleTask" | "timeConstraintTask";

interface Task {
    "@type": TaskType;
    id: string;

    name: string;

    description: string;

    completed: boolean;

}

class SimpleTask implements Task {
    "@type": TaskType = "simpleTask";
    id: string;

    name: string;

    description: string;

    completed: boolean;

    constructor(id: string, name: string, description: string, completed: boolean) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.completed = completed;
    }
}

class TimeConstraintTask implements Task {
    "@type": TaskType = "timeConstraintTask";
    id: string;

    name: string;

    description: string;

    completed: boolean;

    dueDate: string;

    expired: boolean;

    constructor(id: string, name: string, description: string, completed: boolean, dueDate: string, expired: boolean) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.completed = completed;
        this.dueDate = dueDate;
        this.expired = expired;
    }
}

export {Task, SimpleTask, TimeConstraintTask};