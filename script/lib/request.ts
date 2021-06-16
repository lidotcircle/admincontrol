import 'process';
import assert from 'assert';
import { default as fetch } from 'node-fetch';
import { API } from './api';
import { ArgvSpec, generateAgrvHelp, parseArgv, printJSONResponse } from './util';
import { meeting_handler } from './meeting';
import { meeting_comment_handler } from './meeting_comment';


async function ping() //{
{
    const resp = await fetch(API.ping);
    await printJSONResponse(resp);
} //}

const argvSpec: ArgvSpec[] = [
    { leader: 'meeting', description: '会议相关命令', },
    { leader: 'meetingComment', description: '会议评论', },
    { leader: 'ping',    description: '测试服务的可达性', },
    {},
    { leader: '-h', longLeader: '--help', description: '显示帮助', }
];
async function main(argv: string[]) {
    assert(Array.isArray(argv) && argv.length > 0);
    const cmd = argv[0];
    argv = argv.slice(1);

    switch(cmd) {
        case 'ping':
            await ping();
            break;

        case 'meeting':
            await meeting_handler(argv);
            break;

        case 'meetingComment':
            await meeting_comment_handler(argv);
            break;

        case '-h':
        case '--help':
            const stv = `Usage: request.js [command]\n\n` + generateAgrvHelp(argvSpec, 120, 4).join('\n');
            console.log(stv);
            break;

        default:
            throw new Error(`command ${cmd} is unsupported`);
            break;
    }
}

assert(process.argv.length >= 3);
main(process.argv.slice(2)).catch(err => console.error(err));

