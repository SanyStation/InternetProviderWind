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
        
        div.append("<li><a href=\"#\">...</a></li>")
        
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