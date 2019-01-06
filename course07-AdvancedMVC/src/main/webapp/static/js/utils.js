// 后端API接口
const apis = {
    'spittles': {
        'find': '/spittles/find',
        'save': '/spittles/save',
        'findOne': '/spittles/'
    },

    'spitter': {
        'register': '/spitter/register',
        'profile': '/spitter/'
    }
};

const avatarRootPath = '/static/img/avatar/';

// 获取URL后面参数列表
function getUrlParam(key) {
    let regx = new RegExp("(^|&)" + key + "=([^&]*)(&|$)", "i");
    let r = window.location.search.substr(1).match(regx);
    if (r != null)
        return decodeURI(r[2]);
    return '';
}

// 排除undefined
function excludeUndefined(str) {
    return str === undefined ? '' : str;
}