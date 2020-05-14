import Cookies from "universal-cookie";

function getCookieData(key: string): string {
  const cookies = new Cookies();
  const data: string = cookies.get('userId');

  return data;
}

export default getCookieData;