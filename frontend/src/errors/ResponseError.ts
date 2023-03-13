export default class ResponseError extends Error {
    public readonly status: number;

    constructor(status: number) {
        super();
        this.status = status;
    }
}