function hasElem(_tp, _side) {
    var e = getElem(_tp)
    return e != null && e.data('side') == _side
}
function targetPos(elem) {
    var lt = {x: 21, y: 32}
    var offSet = 14
    var cur_x = $(elem).position().left + ($(elem).width()/2);
    var cur_y = $(elem).position().top + ($(elem).height()/2);
    for (var i = 0; i < 10; i++) {
        for (var j = 0; j < 9; j++) {
            var temp = {x: lt.x + j*40, y: lt.y + i*40}
            if ((cur_x > (temp.x - offSet) && cur_y > (temp.y - offSet))
                && (cur_x < (temp.x + offSet) && cur_y < (temp.y + offSet))) {
                return {x: i, y: j};
            }
        }
    }
    return null;
}
function moveStopped() {
    var tp = targetPos(this)
    var index = $(this).data('elemId') > 7 ? $(this).data('elemId') - 7 : $(this).data('elemId')
    var dest
    if (tp && (tp.x != $(this).data('x') || tp.y != $(this).data('y'))
        && !hasElem(tp, $(this).data('side')) && moveFuncs['canMoveTo' + index]($(this), tp)) {
        $(this).data('x', tp.x).data('y', tp.y)
        dest = {top: getTop(tp.x), left: getLeft(tp.y)}
    } else {
        dest = {top: $(this).data('oldPos').top, left: $(this).data('oldPos').left}
    }
    $(this).draggable('disable')
    $(this).data('oldPos', {top: dest.top, left: dest.left})
    $(this).animate(dest, 'slow', function() {$(this).draggable('enable')})
}
function elemsInLine(line) {
    var tl = line
    if (line['d'] == 'h') {
        return $(allElemSelector()).filter(function() {
            return $(this).data('x') == tl['x'] && $(this).data('y') > tl['start'] && $(this).data('y') < tl['end']
        })
    } else {
        return $(allElemSelector()).filter(function() {
            return $(this).data('y') == tl['y'] && $(this).data('x') > tl['start'] && $(this).data('x') < tl['end']
        })
    }
}
function determineLine(elem, tp) {
    var line = {}
    if (elem.data('x') == tp.x) {
        line['d'] = 'h'; line['x'] = tp.x
        if (elem.data('y') > tp.y) {
            line['start'] = tp.y; line['end'] = elem.data('y')
        } else {
            line['start'] = elem.data('y'); line['end'] = tp.y
        }
    }
    if (elem.data('y') == tp.y) {
        line['d'] = 'v'; line['y'] = tp.y
        if (elem.data('x') > tp.x) {
            line['start'] = tp.x; line['end'] = elem.data('x')
        } else {
            line['start'] = elem.data('x'); line['end'] = tp.x
        }
    }
    return line;
}
var moveFuncs = {
    canMoveTo1: function(elem, tp) {
        if (tp.y > 5 || tp.y < 3 || (tp.x > 2 && tp.x < 7)) return false

        if ((Math.abs(tp.x - elem.data('x')) == 1 && (tp.y == elem.data('y')))
            || ((tp.x == elem.data('x')) && (Math.abs(tp.y - elem.data('y')) == 1))) {
            return true;
        }
        return false;
    },
    canMoveTo2: function(elem, tp) {
        if (elem.data('side') == 'black') {
            if (elem.data('x') <= 4) {
                return (tp.x - elem.data('x') == 1) && (tp.y == elem.data('y'))
            } else {
                return ((tp.x - elem.data('x') == 1) && (tp.y == elem.data('y'))) || ((tp.x == elem.data('x')) && Math.abs(tp.y - elem.data('y')) == 1)
            }
        } else {
            if (elem.data('x') >= 5) {
                return (tp.x - elem.data('x') == -1) && (tp.y == elem.data('y'))
            } else {
                return ((tp.x - elem.data('x') == -1) && (tp.y == elem.data('y'))) || ((tp.x == elem.data('x')) && Math.abs(tp.y - elem.data('y')) == 1)
            }
        }
    },
    canMoveTo3: function(elem, tp) {
        if (elem.data('x') != tp.x && elem.data('y') != tp.y)
            return false;
            
        var elems = elemsInLine(determineLine(elem, tp))
        return elems.size() == 0
    },
    canMoveTo4: function(elem, tp) {
        var startH = elem.data('y') > tp.y ? tp.y : elem.data('y')
        var endH = elem.data('y') < tp.y ? tp.y : elem.data('y')
        var startV = elem.data('x') > tp.x ? tp.x : elem.data('x')
        var endV = elem.data('x') < tp.x ? tp.x : elem.data('x')
        if (Math.abs(elem.data('x') - tp.x) == 1 && (Math.abs(elem.data('y') - tp.y)) == 2) {
            if (elemsInLine({d: 'h', x: elem.data('x'), start: startH, end: endH}).size() == 0) {
                return true;
            }
        }
        if (Math.abs(elem.data('x') - tp.x) == 2 && (Math.abs(elem.data('y') - tp.y)) == 1) {
            if (elemsInLine({d: 'v', y: elem.data('y'), start: startV, end: endV}).size() == 0) {
                return true;
            }
        }
        return false;
    },
    canMoveTo5: function(elem, tp) {
        if (elem.data('x') != tp.x && elem.data('y') != tp.y)
            return false;
            
        var elems = elemsInLine(determineLine(elem, tp))
        var elemInTar = getElem(tp)
        return (elems.size() == 1 && (elemInTar != null && elemInTar.data('side') != elem.data('side'))) || (elems.size() == 0 && elemInTar == null)
    },
    canMoveTo6: function(elem, tp) {
        var elemInTar = getElem(tp)
        if ((elem.data('x') == 0 || elem.data('x') == 2) && (elem.data('y') == 3 || elem.data('y') == 5))
            return (tp.x == 1 && tp.y == 4)
        if (elem.data('x') == 1 && elem.data('y') == 4)
            return (tp.x == 0 && tp.y == 3) || (tp.x == 0 && tp.y == 5) || (tp.x == 2 && tp.y == 3) || (tp.x == 2 && tp.y == 5)
        if ((elem.data('x') == 7 || elem.data('x') == 9) && (elem.data('y') == 3 || elem.data('y') == 5))
            return (tp.x == 8 && tp.y == 4)
        if (elem.data('x') == 8 && elem.data('y') == 4)
            return (tp.x == 7 && tp.y == 3) || (tp.x == 7 && tp.y == 5) || (tp.x == 9 && tp.y == 3) || (tp.x == 9 && tp.y == 5)
        return false
    },
    canMoveTo7: function(elem, tp) {
        var cur_x = elem.data('x')
        var cur_y = elem.data('y')
        if (2 != Math.abs(cur_x-tp.x) || 2 != Math.abs(cur_y-tp.y))
            return false

        if ((2 == (cur_x-tp.x) && 2 == (cur_y-tp.y)) && cur_x != 5)
            return null == getElem({x: cur_x - 1, y: cur_y - 1})
        if ((2 == (cur_x-tp.x) && -2 == (cur_y-tp.y)) && cur_x != 5)
            return null == getElem({x: cur_x - 1, y: cur_y + 1})
        if ((-2 == (cur_x-tp.x) && 2 == (cur_y-tp.y)) && cur_x != 4)
            return null == getElem({x: cur_x + 1, y: cur_y - 1})
        if ((-2 == (cur_x-tp.x) && -2 == (cur_y-tp.y)) && cur_x != 4)
            return null == getElem({x: cur_x + 1, y: cur_y + 1})
        return false;
    }
}
function findAllElement() {
    return $(allElemSelector());
}
function allElemSelector() {
    return "#chessCanvas div";
}
function getElem(_tp) {
    var tp = _tp
    var result = findAllElement().filter(function() {
        return $(this).data('x') == tp.x && $(this).data('y') == tp.y
    })
    return result.size() != 0 ? $(result[0]) : null;
}
function getTop(x) {
    return x * 40 + 15
}
function getLeft(y) {
    return y * 40 + 4;
}
$.fn.g_chessElement = function() {
    return this.each(function() {
        var elemId = $(this).data('elemId')
        var x = $(this).data('x')
        var y = $(this).data('y')
        var top = getTop(x)
        var left = getLeft(y)
        $(this).addClass('elem').css("top", top + "px").css('left', left + "px").css('position', 'absolute')
        .addClass('elem-' + elemId).hover(function(){$(this).css('cursor', 'move')}).data('side', elemId > 7 ? 'red' : 'black')
        .draggable({stack: allElemSelector(), containment: 'parent', cursor: 'move',
                    stop: moveStopped}).appendTo('#chessCanvas')
        .mousedown(function () {
            findAllElement().each(function () {
                $(this).removeClass('elem-' + $(this).data('elemId') + '-selected');
            });
            $(this).addClass('elem-' + $(this).data('elemId') + '-selected').data('oldPos', {top: $(this).position().top, left: $(this).position().left})
        })
    })
}

function createNew(data) {
    $('#chessCanvas').data({cp: 'red'})
    $("<div>").data({elemId: 1, x: 0, y: 4}).g_chessElement()
    $("<div>").data({elemId: 2, x: 3, y: 0}).g_chessElement()
    $("<div>").data({elemId: 2, x: 3, y: 2}).g_chessElement()
    $("<div>").data({elemId: 2, x: 3, y: 4}).g_chessElement()
    $("<div>").data({elemId: 2, x: 3, y: 6}).g_chessElement()
    $("<div>").data({elemId: 2, x: 3, y: 8}).g_chessElement()
    $("<div>").data({elemId: 3, x: 0, y: 0}).g_chessElement()
    $("<div>").data({elemId: 3, x: 0, y: 8}).g_chessElement()
    $("<div>").data({elemId: 4, x: 0, y: 1}).g_chessElement()
    $("<div>").data({elemId: 4, x: 0, y: 7}).g_chessElement()
    $("<div>").data({elemId: 5, x: 2, y: 1}).g_chessElement()
    $("<div>").data({elemId: 5, x: 2, y: 7}).g_chessElement()
    $("<div>").data({elemId: 6, x: 0, y: 3}).g_chessElement()
    $("<div>").data({elemId: 6, x: 0, y: 5}).g_chessElement()
    $("<div>").data({elemId: 7, x: 0, y: 2}).g_chessElement()
    $("<div>").data({elemId: 7, x: 0, y: 6}).g_chessElement()

    $("<div>").data({elemId: 8, x: 9, y: 4}).g_chessElement()
    $("<div>").data({elemId: 9, x: 6, y: 0}).g_chessElement()
    $("<div>").data({elemId: 9, x: 6, y: 2}).g_chessElement()
    $("<div>").data({elemId: 9, x: 6, y: 4}).g_chessElement()
    $("<div>").data({elemId: 9, x: 6, y: 6}).g_chessElement()
    $("<div>").data({elemId: 9, x: 6, y: 8}).g_chessElement()
    $("<div>").data({elemId: 10, x: 9, y: 0}).g_chessElement()
    $("<div>").data({elemId: 10, x: 9, y: 8}).g_chessElement()
    $("<div>").data({elemId: 11, x: 9, y: 1}).g_chessElement()
    $("<div>").data({elemId: 11, x: 9, y: 7}).g_chessElement()
    $("<div>").data({elemId: 12, x: 7, y: 1}).g_chessElement()
    $("<div>").data({elemId: 12, x: 7, y: 7}).g_chessElement()
    $("<div>").data({elemId: 13, x: 9, y: 3}).g_chessElement()
    $("<div>").data({elemId: 13, x: 9, y: 5}).g_chessElement()
    $("<div>").data({elemId: 14, x: 9, y: 2}).g_chessElement()
    $("<div>").data({elemId: 14, x: 9, y: 6}).g_chessElement()
    $("#createNew").button("disable")
}
