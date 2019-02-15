/**
 * Object returned in user info request. Only few parameters was mapped.
 */
export interface User {
    authenticated: string;
    clientOnly: string;
    name: string;
}
