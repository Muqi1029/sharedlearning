import axios from "axios";
import cheerio from "cheerio";

export async function fetchUrlData(url: string): Promise<string | undefined> {
  try {
    const response = await axios.get(url);
    const html = response.data;
    const $ = cheerio.load(html);
    return $("title").text();
  } catch (e) {
    console.log(e);
  }
}
