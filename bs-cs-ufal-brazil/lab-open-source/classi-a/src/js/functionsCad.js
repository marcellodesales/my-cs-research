function validatePrice(price)
{
	re =  /^[0-9]+\,[0-9]{2}$/;
	return re.test(price);	
}

function updatePrice(price){	
	re = /,/gi;
	return newPrice = price.replace(re,".");
}

function validateData(data)
{
	re = /^[0-9]{2}\/[0-9]{2}\/[0-9]{4}$/;
	return re.test(data);	
}

function returnDate(data)
{
	dia = data.substring(0,2);
	mes = data.substring(3,5);
	ano = data.substring(6,10);
	return ano+"/"+mes+"/"+dia;	
}

function validateImage(img)
{
	re =  /^w+\.[jpg|gif]$/;
	return re.test(img);	
}