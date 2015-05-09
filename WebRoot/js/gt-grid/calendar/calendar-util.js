function timeSelect(inputField,tributton)
{    
    Calendar.trigger(
    {
    	
        inputField     :    inputField,  
        //ifFormat       :    "%Y-%m-%d %H:%M:%S",
        ifFormat       :    "%Y-%m-%d",  
        showsTime      :    true,
        button         :    tributton,
        singleClick    :    true,
        step	       :	1		
	}
	);
}



function timeSelector(inputField,tributton)
{
    /*Calendar.setup({
        inputField     :    inputField,     // id of the input field
        ifFormat       :    "%Y-%m-%d %H:%M",      // format of the input field
        showsTime      :    timeFlag,
        timeFormat     :    "24",
        button         :    tributton,  // trigger for the calendar (button ID)
        align          :    "Bl",           // alignment (defaults to "Bl")
        singleClick    :    true,
        weekNumbers    :    false
    });
    */
    Calendar.trigger({
        inputField     :    inputField,   // id of the input field
//        ifFormat       :    "%Y-%m-%d %H:%M",       // format of the input field
        ifFormat       :    "%Y-%m-%d %H:%M",
        showsTime      :    true,
        timeFormat     :    "24",
		button         :    tributton,
        align          :    "Br",
        onUpdate       :    null,
		singleClick    :    true,
        weekNumbers    :    false,
		step	       :	1
    });
}

function dateSelector(inputField,tributton)
{    
    Calendar.trigger({
        inputField     :    inputField,   // id of the input field
        ifFormat       :    "%Y-%m-%d",       // format of the input field
        button         :    tributton,
        weekNumbers    :    true,
		singleClick    :    true,
		step	       :	1
    });
}

function yearSelector(inputField,tributton)
{    
    Calendar.trigger({
        inputField     :    inputField,   // id of the input field
        ifFormat       :    "%Y",       // format of the input field
        align          :    "Br",
		button         :    tributton,
        onUpdate       :    null,
        weekNumbers    :    false,
		singleClick    :    true,
		step	       :	1
    });
}

function yymmSelector(inputField,tributton)
{    
    Calendar.trigger({
        inputField     :    inputField,   // id of the input field
        ifFormat       :    "%Y-%m",       // format of the input field
        align          :    "Br",
		button         :    tributton,
        onUpdate       :    null,
        weekNumbers    :    false,
		singleClick    :    true,
		step	       :	1
    });
}