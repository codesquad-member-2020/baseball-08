const fetchRequest = (url, method, userId) => {
  return fetch(url, {
    method: method,
    mode: "cors",
    cache: "no-cache",
    headers: {
      "Content-Type": "application/json",
      "userId": userId
    },
  });
};

export default fetchRequest;