import { API } from "../lib/api";
import fetch from "node-fetch";
import { URL, URLSearchParams } from "url";


test("ping", async () => {
    let url = new URL(API.Ping.ping);
    let resp = await fetch(url);
    expect(resp.status).toBe(200);
    let text = await resp.text();
    expect(text).not.toBeNull();
    let respobj = JSON.parse(text);
    expect(respobj["msg"]).toBeNull();
    expect(respobj["code"]).toBe(0);
    expect(respobj["data"]).not.toBeNull();

    url = new URL(API.Ping.notFound);
    resp = await fetch(url);
    expect(resp.status).toBe(404);
    text = await resp.text();
    expect(text).not.toBeNull();
    respobj = JSON.parse(text);
    expect(respobj["msg"]).not.toBeNull();
    expect(respobj["code"]).toBe(404);
    expect(respobj["data"]).toBeNull();
});

