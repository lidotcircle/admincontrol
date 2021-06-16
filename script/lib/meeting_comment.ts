import fetch from "node-fetch";
import { URL, URLSearchParams } from "url";
import { API } from "./api";
import { ArgvSpec, generateAgrvHelp, parseArgv, printJSONResponse, Regex } from "./util";


const commentArgvSpec: ArgvSpec[] = [
    {name: 'meetingId', leader: '-m', longLeader: '--meeting',  required: true,  followN: 1, description: '会议ID', valueConverter: parseInt},
    {name: 'comment',   leader: '-c', longLeader: '--comment', required: true,  followN: 1, description: '评论内容'},
    {},
    {leader: '-h', longLeader: '--help', description: '显示帮助'}
];

const deleteCommentArgvSpec: ArgvSpec[] = [
    {name: 'meetingId', leader: '-m', longLeader: '--meeting', required: true, followN: 1, description: '会议ID', valueConverter: parseInt},
    {name: 'index',     leader: '-i', longLeader: '--index',   required: true, followN: 1, description: '评论索引', valueConverter: parseInt},
    {},
    {leader: '-h', longLeader: '--help', description: '显示帮助'}
];

const argvSpec: ArgvSpec[] = [
    {leader: 'comment', description: '评论'},
    {leader: 'delete',  description: '删除评论'},
    {},
    {leader: '-h', longLeader: '--help', description: '显示帮助'}
];


async function comment(obj: object) {
    console.log(obj);
    const resp = await fetch(API.Meeting.comment, {
        method: "POST",
        body: JSON.stringify(obj),
        headers: {"Content-Type": "application/json"},
    });

    printJSONResponse(resp, false);
}

async function deleteComment(obj: object) {
    console.log(obj);
    const url = new URL(API.Meeting.comment);
    url.search = new URLSearchParams(obj as any).toString();
    const resp = await fetch(url, {
        method: "DELETE"
    });

    printJSONResponse(resp, false);
}


export async function meeting_comment_handler(cmds: string[]) {
    const cmd = cmds[0];
    cmds = cmds.slice(1);

    switch(cmd) {
        case "comment": {
            if(cmds[0] == '-h' || cmds[0] == '--help') {
                console.log(`Usage: request.js meetingComment comment [options]\n\n` + 
                            generateAgrvHelp(commentArgvSpec, 120, 4).join('\n'));
                return;
            }
            const [themap, _] = parseArgv(commentArgvSpec, cmds);
            await comment(themap);
        } break;

        case "delete": {
            if(cmds[0] == '-h' || cmds[0] == '--help') {
                console.log(`Usage: request.js meetingComment delete [options]\n\n` + 
                            generateAgrvHelp(deleteCommentArgvSpec, 120, 4).join('\n'));
                return;
            }
            const [themap, _] = parseArgv(deleteCommentArgvSpec, cmds);
            await deleteComment(themap);
        } break;

        case "-h":
        case "--help":
            console.log(`Usage: request.js meetingComment [command]\n\n` + 
                        generateAgrvHelp(argvSpec, 120, 4).join('\n'));
            break;

        default:
            throw new Error(`unsupported command '${cmd}'`);
    }
}

