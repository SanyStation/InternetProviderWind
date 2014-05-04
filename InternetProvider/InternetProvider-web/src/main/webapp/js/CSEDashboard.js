/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function CSEDashboard() {
    this.tables = [
        "TABLE1", 
        "TABLE2",
    ]
    /* Кол-во элементов в таблице */
    this.elementCount = {
        test : 322
    };
    this.data = {
        test : {}
    };
    /* Количество элементов, выводимое на страницу */
    this.tableSize = {
        test : 25
    };
    this.pageLimit = 5;
};

CSEDashboard.prototype.setElementCount = function(table, elementCount) {
    this.elementCount[table] = elementCount;
};

CSEDashboard.prototype.getTableSize = function(table) {
    return this.tableSize[table];
}

CSEDashboard.prototype.setData = function(table, data) {
    this.data[table] = data;
}

CSEDashboard.prototype.getElementCount = function(command, table) {
    
    return $.ajax({
                url : "Controller",
                data : {
                    command : command,
                    table : table,            
                },
                success : function(data) {
                    return data;
                }
            });
    
};

CSEDashboard.prototype.getElementFromOffset = function(command, table, offset) {
    
    var count = this.getTableSize(table);
    
    return $.ajax({
                url : "Controller",
                data : {
                    command : command,
                    table : table, 
                    count : count,
                    offset : offset,
                },
                success : function(data) {
                    return data;
                }
            });
    
};
CSEDashboard.prototype.drawSITable = function(table) {

    var size = this.elementCount[table];
    var data = this.data[table];
    var pages = Math.ceil(size / this.tableSize[table]);
    var div = $("#sitable");
    div.append("<table>");
    div.append("<tr>");
    div.append("<td> SI_id </td>");
    div.append("<td> User_id </td>");
    div.append("<td> User_name </td>");
    div.append("<td> Service_order_id </td>");
    div.append("<td> Service_id </td>");
    div.append("<td> Service_name </td>");
    div.append("<td> SI_status </td>");
    div.append("</tr>");
    for (var i = 0; i < pages; i++) {
        div.append("<tr>");
        div.append("<td>" + data[i]["SI_id"] + "</td>");
        div.append("<td>" + data[i]["User_id "] + "</td>");
        div.append("<td>" + data[i]["User_name"] + "</td>");
        div.append("<td>" + data[i]["Service_order_id"] + "</td>");
        div.append("<td>" + data[i]["Service_id"]+ "</td>");
        div.append("<td>" + data[i]["Service_name"]+ "</td>");
        div.append("<td>" + data[i]["SI_status"]+ "</td>");
        div.append("</tr>");

    }
     div.append("</table>");
};
CSEDashboard.prototype.drawPaginationTable = function(table) {
    
    var size = this.elementCount[table];
    var pages = Math.ceil(size / this.tableSize[table]);
    var div = $("#pagination > ul");
    
    div.append("<li><a href=\"#\">&laquo</a></li>");
    
    if(pages < this.pageLimit) {
        for(var i = 0; i < pages; i++) {
            div.append("<li><a href=\"#\">" + (i + 1) +"</a></li>");                        
        }  
    } else {
        for(var i = 0; i < Math.floor(this.pageLimit / 2); i++) {
            div.append("<li><a href=\"#\">" + (i + 1) +"</a></li>");                        
        }
        
        div.append("<li><a href=\"#\">...</a></li>");
        
        for(var i = pages - Math.floor(this.pageLimit / 2); i < pages; i++) {
            div.append("<li><a href=\"#\">" + (i + 1) +"</a></li>");                        
        }        
    }
    
    div.append("<li><a href=\"#\">&raquo</a></li>");
    
};

CSEDashboard.prototype.setPageEventHanlers = function(table) {
    var div = $("#pagination > ul");
    var count = this.tableSize[table];
    var _this_ = this;
    
    var links = div.find("a");
    for(var i = 1; i < links.size() - 1; i++) {
        var value = $(links[i]).text(); 
        
        switch(value) {
            case "..." :
                break;
            default:     
                var page = parseInt(value);
                
                var f = function(event) {
                    var offset = (event.data.page - 1) * (event.data.count);
                    console.log(offset);
                    event.data._this_.getElementFromOffset("COMMAND", event.data.table, offset);
                }
                
                $(links[i]).on("click", 
                    {
                        page : page,
                        count : count,
                        table : table,
                        _this_ : _this_
                    }, f);
        }   
    }

};