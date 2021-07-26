import 'process';


const endpoint = process.env["ADMIN_CONTROL_APIS"] || 'http://localhost:8012/apis';

export module API {
    export module Ping {
        export const ping     = `${endpoint}/ping`;
        export const notFound = `${endpoint}/ping/not-found`;
    }

    export module Meeting {
        export const index = `${endpoint}/meeting`;
        export const day30 = `${endpoint}/meeting/latest-thirty-days`;
        export const oneday = `${endpoint}/meeting/day-list`;

        export const comment = `${endpoint}/meeting/comment`;
    }

    export module APIAuth {
        export const magic = `${endpoint}/auth-token/magic`;
    }

    export module User {
        export const post = `${endpoint}/user/register`;
        export const put  = `${endpoint}/user`;
        export const get  = `${endpoint}/user`;
        export const deleteUser  = `${endpoint}/user`;
    }

    export module RefreshToken {
        export const post = `${endpoint}/auth/refresh-token`;
        export const deleteToken = `${endpoint}/auth/refresh-token`;
    }

    export module JWT {
        export const get = `${endpoint}/auth/jwt`;
    }
}

