import { API } from "../lib/api";
import fetch from "node-fetch";
import { URL, URLSearchParams } from "url";


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

