import 'process';
import assert from 'assert';
import { default as fetch } from 'node-fetch';
import { API } from './api';
import { printJSONResponse } from './util';


async function ping() //{
{
    const resp = await fetch(API.ping);
    await printJSONResponse(resp);
} //}

async function main(argv: string[]) {
    assert(Array.isArray(argv) && argv.length > 0);
    const cmd = argv[0];

    switch(cmd) {
        case 'ping':
            await ping();
            break;

        default:
            throw new Error(`command ${cmd} is unsupported`);
            break;
    }
}

assert(process.argv.length >= 3);
main(process.argv.slice(2)).catch(err => console.error(err));

