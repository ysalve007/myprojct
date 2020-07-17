$(function(){
    /**
     * loading validation engine in all forms
     */
    $('form').validationEngine();
    /**
     * when account type is selected
     */
    $('#accountType').change(function(){
        val = $(this).val();
        //hidding tab 2 for for all
        $('#customer2Tab').removeAttr('data-toggle');
        if(val !== 'Single'){
            $('#customer2Tab').attr({
                'data-toggle':'tab'
            });
        }
    }).trigger('change');
    
     $("#index1,h1").hover(function(){
        $(this).css("color","#CC3300 ");},function(){
        $(this).css("color","#000000");
        });
        //for conflictresolve.jsp table
        $("#resolveTable tr").hover(function(){
            $(this).css("background-color","#FFE4B5");},function(){
            $(this).css("background-color","#87CEFA");
        });
});