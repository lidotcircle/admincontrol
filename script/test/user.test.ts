import { API } from "../lib/api";
import fetch from "node-fetch";
import { URL, URLSearchParams } from "url";
import { jwt, login } from "./login";


test("user-create", async () => {
    let url = new URL(API.APIAuth.magic);
    url.search = new URLSearchParams({operation: "register", subject: "13012345678"}).toString();
    let resp = await fetch(url);
    expect(resp.status).toBe(200);
    let text = await resp.text();
    let obj = JSON.parse(text);
    let token = obj["data"];
    expect(token).not.toBeNull();

    url = new URL(API.User.post);
    resp = await fetch(url, {
        method: "POST",
        headers: {
            "X-Authorization": token,
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            userName: "testUser",
            phone:    "13012345678",
            password: "password",
        })
    });
    expect(resp.status).toBe(200);

    const refreshToken = await login("testUser", "password");
    expect(refreshToken).not.toBeNull();
    const jwttoken = await jwt(refreshToken);
    expect(jwttoken).not.toBeNull();

    url = new URL(API.User.deleteUser);
    url.search = new URLSearchParams({password: "password"}).toString();
    resp = await await fetch(url, {
        method: "DELETE",
        headers: {
            "X-Auth": jwttoken,
        },
    });
    expect(resp.status).toBe(200);
});

