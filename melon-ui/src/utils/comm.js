import { isArray, isString } from 'lodash';

export function tansParams(params) {
  let result = '';
  for (const propName of Object.keys(params)) {
    const value = params[propName];
    var part = encodeURIComponent(propName) + '=';
    if (value !== null && value !== '' && typeof value !== 'undefined') {
      if (typeof value === 'object') {
        for (const key of Object.keys(value)) {
          if (value[key] !== null && value[key] !== '' && typeof value[key] !== 'undefined') {
            const params = propName + '[' + key + ']';
            var subPart = encodeURIComponent(params) + '=';
            result += subPart + encodeURIComponent(value[key]) + '&';
          }
        }
      } else {
        result += part + encodeURIComponent(value) + '&';
      }
    }
  }
  return result;
}

// 验证是否为blob格式
export async function blobValidate(data) {
  try {
    const text = await data.text();
    JSON.parse(text);
    return false;
  } catch (error) {
    return true;
  }
}

export const clearObj = (obj) => {
  for (const key in obj) {
    if (isArray(obj[key])) {
      obj[key] = [];
    } else if (isString(obj[key])) {
      obj[key] = '';
    } else {
      obj[key] = null;
    }
  }
};

const createColor = () => {
  const r = randomInt(255);
  const g = randomInt(255);
  const b = randomInt(255);
  const c = `#${r.toString(16)}${g.toString(16)}${b.toString(16)}000`;
  return c.slice(0, 7);
};

const randomInt = (max) => {
  return Math.floor(Math.random() * max);
};

export const randomColor = () => {
  return `linear-gradient(${180}deg, ${createColor()} 0%, ${createColor()} 100%)`;
  // return '#' + Math.floor(Math.random() * 256).toString(10);
};
