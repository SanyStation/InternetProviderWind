/*
 * displayTagAjax.js
 *
 * Copyright (c) 2008 Vijay Karthik
 * Licensed under GPL (GPL-LICENSE.txt) license.
 */

function changeLinks()
{
    done=true;
    var linkData, queryArr, action, qryStr;
    selector = 'span.pagelinks>a';
    changeUsingSelector(selector);
    selector = 'table#dispTable>thead>tr>th>a';
    changeUsingSelector(selector);
}
function changeUsingSelector(selector)
{
    $(selector).each(function()
    {
        linkData = $(this).attr("href");
        queryArr = linkData.split("?");
        action = queryArr[0];
        qryStr = queryArr[1];
        newStr = "JavaScript:doAjax('"+action+"','"+qryStr+"','ajxDspId');";
        $(this).attr("href",newStr);
        $(this).attr("onMouseOver","window.status='Pagination Links have been Ajaxified!!';return false;");
        $(this).attr("onMouseOut","window.status='';return false;");
    });    

}
   
    
jQuery(function($) {
    changeLinks();
}); 


function doAjax(url, data, eleId)
{
    //if you need additional params to be passed - add to the data variable
    $.ajax
    ({
        url: url,
        data:  data,
        async: false,
        success: function(resp){
            var d = $(resp); 
            htmlStr="";
            for(i=0;i<d.length;i++)
            {
                var node = d[i];
                if (node.id == eleId)
                {
                    htmlStr=$(node).html(); 
                    break;
                }
            }
            $('#'+eleId).html(htmlStr);
            changeLinks();
        }

    });
}