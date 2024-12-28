import https from 'https';

const defaultUrl = "https://www.amazon.com";

export const handler = async function(event) {
    const url = event?.url || defaultUrl;
    console.log(`Checking URL: ${url}`);
    let statusCode;
    await new Promise(function(resolve, reject) {
        https.get(url, (res) => {
            statusCode = res.statusCode;
            resolve(statusCode);
        }).on("error", (e) => {
            reject(Error(e));
        });
    });
    console.log(statusCode);
    return statusCode;
};