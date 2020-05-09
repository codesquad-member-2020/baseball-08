function compareJSONObject(source: string, dest: string): boolean {
  return JSON.stringify(source) === JSON.stringify(dest);
}

export default compareJSONObject;