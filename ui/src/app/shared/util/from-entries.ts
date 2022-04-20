export const fromEntries = <T>(entries: Array<[string, T]>): Record<string, T> => {
  return entries.reduce((acc, [key, value]) => {
    acc[key] = value;
    return acc;
  }, {} as Record<string, T>);
};
