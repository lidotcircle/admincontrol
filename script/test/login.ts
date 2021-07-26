import { API } from "../lib/api";
import fetch from "node-fetch";
import { URL, URLSearchParams } from "url";


export async function login(username: string, password: string): Promise<string> {
    let url = new URL(API.APIAuth.magic);
    url.search = new URLSearchParams({operation: "login", subject: username}).toString();

    let resp = await fetch(url);;
    const token = JSON.parse(await resp.text());

    url = new URL(API.RefreshToken.post);
    resp = await fetch(url, {
        method: "POST",
        headers: {
            "X-Authorization": token,
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            userName: username,
            password: password,
        }),
    });

    const respObj = JSON.parse(await resp.text());
    return respObj["data"];
}

export async function logout(token: string): Promise<void> {
    const url = new URL(API.RefreshToken.deleteToken);
    url.search = new URLSearchParams({refreshToken: token}).toString();

    const resp = await fetch(url, {
        method: "DELETE",
    });
    if (resp.status >= 400) {
        throw await resp.text();
    }
}

export async function jwt(token: string): Promise<string> {
    const url = new URL(API.JWT.get);
    url.search = new URLSearchParams({refreshToken: token}).toString();

    const resp = await fetch(url);
    const respObj = await JSON.parse(await resp.text());
    return respObj["data"];
}

