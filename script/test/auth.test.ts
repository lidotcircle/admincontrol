import { API } from "../lib/api";
import fetch from "node-fetch";
import { URL, URLSearchParams } from "url";
import { createTestUser, deleteTestUser } from "./user";


test("auth-magic", async () => {
    let url = new URL(API.APIAuth.magic);

    url.search = new URLSearchParams({operation: "register"}).toString();
    let resp = await fetch(url);
    expect(resp.status).toBe(200);
    expect(await resp.text()).not.toBeNull();

    url.search = new URLSearchParams({operation: "login"}).toString();
    resp = await fetch(url);
    expect(resp.status).toBe(200);
    expect(await resp.text()).not.toBeNull();

    url.search = new URLSearchParams({operation: "resetPassword"}).toString();
    resp = await fetch(url);
    expect(resp.status).toBe(200);
    expect(await resp.text()).not.toBeNull();

    url.search = new URLSearchParams({operation: "message"}).toString();
    resp = await fetch(url);
    expect(resp.status).toBe(200);
    expect(await resp.text()).not.toBeNull();

    url.search = new URLSearchParams({operation: "nope"}).toString();
    resp = await fetch(url);
    expect(resp.status).toBe(404);
    expect(await resp.text()).not.toBeNull();
});

test("refresh-token", async () => {
    await createTestUser("helloMe");

    let url = new URL(API.APIAuth.magic);
    url.search = new URLSearchParams({operation: "login", subject: "helloMe"}).toString();
    let resp = await fetch(url);
    expect(resp.status).toBe(200);
    const authToken = JSON.parse(await resp.text())["data"];
    expect(authToken).not.toBeNull();

    url = new URL(API.RefreshToken.post);
    resp = await fetch(url, {
        method: "POST",
        body: JSON.stringify({
            userName: "helloMe",
            password: "password",
        }),
        headers: {
            "Content-Type": "application/json",
            "X-Authorization": authToken,
        },
    });
    expect(resp.status).toBe(200);
    const token = JSON.parse(await resp.text())["data"];
    expect(token).not.toBeNull();

    url = new URL(API.JWT.get);
    url.search = new URLSearchParams({refreshToken: token}).toString();
    resp = await fetch(url);
    expect(resp.status).toBe(200);
    expect(await resp.text()).not.toBeNull();

    url = new URL(API.RefreshToken.deleteToken);
    url.search = new URLSearchParams({refreshToken: token}).toString();
    resp = await fetch(url, {
        method: "DELETE",
    });
    expect(resp.status).toBe(200);

    url = new URL(API.JWT.get);
    url.search = new URLSearchParams({refreshToken: token}).toString();
    resp = await fetch(url);
    expect(resp.status).not.toBe(200);

    await deleteTestUser("helloMe");
});

