import 'process';


const endpoint = process.env["ADMIN_CONTROL_APIS"] || 'http://localhost:8012/apis';

export module API {
    export const ping = `${endpoint}/ping`;
}

