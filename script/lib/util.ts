import { Response } from 'node-fetch';

export function searchArg(leader: string, follow: boolean, argv: string[]): [string | boolean, string[]] //{
{
    let o1 = null;
    let o2 = [];
    for(let i=0;i<argv.length;i++) {
        if(argv[i] == leader) {
            if(o1 != null) {
                throw new Error(`duplicated argument ${leader}`);
            }
            if(follow) {
                i++;
                if(i == argv.length) {
                    throw new Error(`missing required argument '${leader} <argument>'`);
                }
                o1 = argv[i];
            } else {
                o1 = true;
            }
            continue;
        }

        o2.push(argv[i]);
    }

    return [o1, o2];
} //}

export interface ArgvSpec {
    leader?: string;
    longLeader?: string;
    name?: string;

    required?: boolean;
    followN?: number;
    description?: string;
    pattern?: RegExp | ((index: number, value: string) => string | null);
    valueConverter?: (value: string) => any;
}
export function parseArgv(spec: ArgvSpec[], argv: string[]): [{[key: string]: string[]}, string[]] //{
{
    const ans = {};
    const remain = [];
    const nv: {[key: string]: ArgvSpec} = {};
    const rs: Set<string> = new Set();
    for(const s of spec) {
        if(s.leader == null && s.longLeader == null && s.required == true) {
            throw new Error("bad argument specification");
        }

        if(nv[s.leader] != null) {
            throw new Error(`duplicated leader ${s.leader}`);
        }
        if(nv[s.longLeader] != null) {
            throw new Error(`duplicated long leader ${s.longLeader}`);
        }

        if(s.leader != null) {
            nv[s.leader] = s;
            if(s.required) rs.add(s.leader);
        }
        if(s.longLeader != null) {
            nv[s.longLeader] = s;
            if(s.required) rs.add(s.longLeader);
        }
    }

    for(let i=0;i<argv.length;i++) {
        const pres = argv[i];

        if(nv[pres] != null) {
            if(remain.length > 0) {
                throw new Error(`bad argument ${pres}`);
            }

            const v = nv[pres];
            if(v.required) {
                if(v.leader) rs.delete(v.leader);
                if(v.longLeader) rs.delete(v.longLeader);
            }

            const name = v.name || v.leader || v.longLeader;
            const follows = ans[name] = [];
            for(let j=1;j<=v.followN || 0;j++) {
                let arg = argv[i + j];

                if(v.pattern != null) {
                    if(v.pattern instanceof RegExp) {
                        if(!arg.match(v.pattern)) {
                            throw new Error(`argument for '${pres}' doesn't satisfy regex ${v.pattern}`);
                        }
                    } else {
                        const r = v.pattern(j - 1, arg);
                        if (r != null) {
                            throw new Error(r);
                        }
                    }
                }
                if(v.valueConverter) {
                    arg = v.valueConverter(arg);
                }

                follows.push(arg);
            }
            if (follows.length == 1) ans[name] = follows[0];

            i += v.followN || 0;
        } else {
            remain.push(pres);
        }
    }

    if(rs.size > 0) {
        const options = [];
        for(const k of rs.keys())
            options.push(k);
        throw new Error(`required option '${options}' doesn't satisfied`);
    }

    return [ans, remain];
} //}

export function generateAgrvHelp(spec: ArgvSpec[], lineMaxLength: number = 80, frontSpace: number = 0): string[] //{
{
    let ans: string[] = [];

    let shortl = 0;
    let longl = 0;
    let requiredChar = false;
    for(const s of spec) {
        shortl = Math.max(shortl, s.leader ? s.leader.length : 0);
        longl  = Math.max(longl,  s.longLeader ? s.longLeader.length : 0);
        requiredChar = requiredChar || s.required;
    }

    for(const s of spec) {
        let mm = ' '.repeat(frontSpace);

        if(requiredChar) {
            if(s.required) {
                mm += '* ';
            } else {
                mm += '  ';
            }
        }

        if(s.leader) {
            mm += s.leader + ' '.repeat(shortl + 2 - s.leader.length);
        } else if (shortl > 0) {
            mm += ' '.repeat(shortl + 2);
        }
        if(s.longLeader) {
            mm += s.longLeader + ' '.repeat(longl + 2 - s.longLeader.length);
        } else if (longl > 0) {
            mm += ' '.repeat(longl + 2);
        }

        if(s.description) {
            const text = s.description.split('\n').join(' ');
            const lines = splitText2Lines(text, lineMaxLength - mm.length);
            const n = mm.length;
            mm += lines[0];
            ans.push(mm.trimRight());

            for(const l of lines.slice(1)) {
                ans.push(' '.repeat(n) + l);
            }
        } else {
            ans.push(mm);
        }
    }

    return ans;
} //}

function splitText2Lines(text: string, maxLength: number): string[] //{
{
    const lines: string[] = [];
    const words: string[] = [];
    const cjregex = /^[\u3400-\u9FBF]$/;
    let w = '';
    for(const c of text) {
        if(c.match(cjregex)) {
            if(w.length > 0) {
                words.push(w);
                w = '';
            }
            words.push(c);
        } else if (c.match(/\s/)) {
            if(w.length > 0) {
                words.push(w);
                w = '';
            }
        } else {
            w += c;
        }
    }
    if(w.length > 0) words.push(w);

    let l = '';
    let len = 0;
    let prev_cj = false;
    for(const word of words) {
        const iscj = !!word.match(cjregex);
        const word_len = iscj ? 2 : word.length;

        if(len + word_len <= maxLength) {
            if(iscj) {
                l += word;
                len += word_len;
            } else {
                l += (prev_cj ? ' ' : '') + word + ' ';
                len += word_len + (prev_cj ? 2 : 1);
            }
        } else {
            lines.push(l);
            l = word;
            len = word_len;
        }

        prev_cj = iscj;
    }
    lines.push(l);

    return lines;
} //}

export function tryAsBoolean(value: string): string | boolean //{
{
    if(value == 'true' || value == 'false') {
        return value == 'true';
    }

    return value;
} //}

export async function printJSONResponse(resp: Response, isjson: boolean = true) //{
{
    console.log(`statusCode: ${resp.status}, statusText: ${resp.statusText}`)
    const msg = (await resp.buffer()).toString();
    if(resp.status >= 400) {
        console.error(msg)
        throw new Error('response error: status >= 400');
    }

    if(isjson) {
        try {
            const ans = JSON.parse(msg);
            console.log(JSON.stringify(ans, null, 4));
            return ans;
        } catch {
            throw new Error('format of response body is\'t JSON');
        }
    }
} //}


export module Regex {
    export const datex     = /\d{4}-(1[0-2]|0?[1-9])-([1-9]|[1-2][0-9]|3[0-1])/;
    export const datetimex = /\d{4}-(1[0-2]|0?[1-9])-([1-9]|[1-2][0-9]|3[0-1]) ([0-5]?\d:){2}([0-5]?\d)/;
}

