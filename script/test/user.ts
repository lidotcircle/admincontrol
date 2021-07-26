import { API } from "../lib/api";
import fetch from "node-fetch";
import { URL, URLSearchParams } from "url";
import { jwt, login } from "./login";


export async function createTestUser(username: string): Promise<void> {
    let url = new URL(API.APIAuth.magic);
    url.search = new URLSearchParams({operation: "register", subject: `${username}@test.com`}).toString();
    let resp = await fetch(url);
    let text = await resp.text();
    let obj = JSON.parse(text);
    let token = obj["data"];

    url = new URL(API.User.post);
    resp = await fetch(url, {
        method: "POST",
        headers: {
            "X-Authorization": token,
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            userName: username,
            email:    `${username}@test.com`,
            password: "password",
        })
    });

    if(resp.status >= 400) {
        throw await resp.text();
    }
}

export async function deleteTestUser(username: string): Promise<void> {
    const refreshToken = await login(username, "password");
    const jwttoken = await jwt(refreshToken);

    const url = new URL(API.User.deleteUser);
    url.search = new URLSearchParams({password: "password"}).toString();
    const resp = await await fetch(url, {
        method: "DELETE",
        headers: {
            "X-Auth": jwttoken,
        },
    });

    if(resp.status >= 400) {
        throw await resp.text();
    }
}

