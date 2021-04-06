library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_unsigned.all; 
entity comparator is
port 
(comanda: in std_logic_vector(1 downto 0);
sumacard,sumaintrodusa,sumabancomat,factura: in integer;
outputfactura,outputretragere:out std_logic);
end entity;			

architecture Behavioral of comparator is   
signal outputfac,outputret:std_logic:='0';
begin 
	process(sumacard,sumaintrodusa,sumabancomat)
	begin 
		if(comanda="11")then
if(sumacard >= factura)then  --sumaintrodusa este factura
	outputfac <='1';
else
	outputfac <='0'; 	
end if;	
end if;
if(comanda="01")then
if(sumacard >= sumaintrodusa and sumaintrodusa<=sumabancomat)then
	outputret<='1';												 
else
	outputret<='0';
end if;
end if;
end process;
outputfactura<=outputfac;
outputretragere<=outputret;
end architecture;