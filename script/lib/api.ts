import 'process';


const endpoint = process.env["ADMIN_CONTROL_APIS"] || 'http://localhost:8012/apis';

export module API {
    export const ping = `${endpoint}/ping`;

    export module Meeting {
        export const index = `${endpoint}/meeting`;
        export const day30 = `${endpoint}/meeting/latest-thirty-days`;
        export const oneday = `${endpoint}/meeting/day-list`;

        export const comment = `${endpoint}/meeting/comment`;
    }
}

