import { fromEntries } from '~/app/shared/util/from-entries';

export const asMap = <T extends { id: string }>(items: T[]): Record<string, T> => {
  return fromEntries(items.map(item => [item.id, item]));
};

