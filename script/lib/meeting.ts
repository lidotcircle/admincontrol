import fetch from "node-fetch";
import { URL, URLSearchParams } from "url";
import { API } from "./api";
import { ArgvSpec, generateAgrvHelp, parseArgv, printJSONResponse, Regex } from "./util";


const creationArgvSpec: ArgvSpec[] = [
    {name: 'date',      leader: '-d', longLeader: '--date',     required: true,  followN: 1, description: '会议时间, 格式 "yyyy-mm-dd HH:MM:SS"', pattern: Regex.datetimex},
    {name: 'title',     leader: '-t', longLeader: '--title',    required: true,  followN: 1, description: '会议标题'},
    {name: 'mark',      leader: '-m', longLeader: '--mark',     required: false, followN: 1, description: '会议标记'},
    {name: 'content',   leader: '-c', longLeader: '--content',  required: true,  followN: 1, description: '会议内容'},
    {name: 'sponsor',                 longLeader: '--sponsor',  required: true,  followN: 1, description: '会议发起人'},
    {name: 'startTime', leader: '-s', longLeader: '--start',    required: true,  followN: 1, description: '会议开始时间 "yyyy-mm-dd HH:MM:SS"', pattern: Regex.datetimex},
    {name: 'endTime',   leader: '-e', longLeader: '--end',      required: true,  followN: 1, description: '会议开始时间 "yyyy-mm-dd HH:MM:SS"', pattern: Regex.datetimex},
    {name: 'location',  leader: '-l', longLeader: '--location', required: true,  followN: 1, description: '会议地点'},
    {},
    {leader: '-h', longLeader: '--help', description: '显示帮助'}
];

const argvSpec: ArgvSpec[] = [
    {leader: 'create', description: '创建会议'},
    {leader: 'get',    description: '获取会议详情'},
    {leader: 'get30',  description: '30天内的会议'},
    {leader: 'getinday',  description: '特定一天的会议'},
    {},
    {leader: '-h', longLeader: '--help', description: '显示帮助'}
];


async function create_meeting(obj: object) {
    console.log(obj);
    const resp = await fetch(API.Meeting.index, {
        method: "POST",
        body: JSON.stringify(obj),
        headers: {"Content-Type": "application/json"},
    });

    printJSONResponse(resp, false);
}

async function get_meeting(meetingId: number) {
    const url = new URL(API.Meeting.index);
    url.search = new URLSearchParams({"meetingId": `${meetingId}`}).toString();
    const resp = await fetch(url, {
        method: "GET"
    });

    printJSONResponse(resp);
}

async function get30_meeting() {
    const url = new URL(API.Meeting.day30);
    url.search = new URLSearchParams().toString();
    const resp = await fetch(url, {
        method: "GET"
    });

    printJSONResponse(resp);
}

async function getinday_meeting(date: string) {
    const url = new URL(API.Meeting.oneday);
    url.search = new URLSearchParams({date: date}).toString();
    const resp = await fetch(url, {
        method: "GET"
    });

    printJSONResponse(resp);
}

export async function meeting_handler(cmds: string[]) {
    const cmd = cmds[0];
    cmds = cmds.slice(1);

    switch(cmd) {
        case "create":
            if(cmds[0] == '-h' || cmds[0] == '--help') {
                console.log(`Usage: request.js meeting create [options]\n\n` + 
                            generateAgrvHelp(creationArgvSpec, 120, 4).join('\n'));
                return;
            }
            const [themap, _] = parseArgv(creationArgvSpec, cmds);
            await create_meeting(themap);
            break;

        case "get":
            await get_meeting(parseInt(cmds[0]));
            break;

        case "get30":
            await get30_meeting();
            break;

        case "getinday":
            await getinday_meeting(cmds[0]);
            break;

        case "-h":
        case "--help":
            console.log(`Usage: request.js meeting [command]\n\n` +
                        generateAgrvHelp(argvSpec, 120, 4).join('\n'));
            break;

        default:
            throw new Error(`unsupported command '${cmd}'`);
    }
}

