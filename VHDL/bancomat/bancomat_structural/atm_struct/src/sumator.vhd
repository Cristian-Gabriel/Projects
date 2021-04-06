library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use std.standard.all;
entity sumator is 
	port(confirmarebani,confirmareretragere,stop:in std_logic;
	retragere,factura:in std_logic;
	comanda: in std_logic_vector(1 downto 0);
	miiout,zeciout,suteout: std_logic_vector(3 downto 0);
	suma_introdusa:out integer;
	suma:out integer);
end sumator;
architecture behavioral of sumator is  
--signal :std_logic:='0';
begin
	process(miiout,zeciout,suteout,comanda,confirmarebani,confirmareretragere)
variable miiint:integer;
variable suteint:integer;
variable zeciint:integer;
variable sumav,sumaintrodusav:integer:=0;
variable sfactura:integer:=100;

begin	
	if(comanda="10")then
	sumaintrodusav:=conv_integer(miiout)*1000+conv_integer(suteout)*100+conv_integer(zeciout)*10;
	miiint:=conv_integer(miiout);
	suteint:=conv_integer(suteout);
	zeciint:=conv_integer(zeciout);
	if(confirmarebani='1')then
		sumav:=sumav+sumaintrodusav;
	end if;
elsif(comanda="01") then
	sumaintrodusav:=conv_integer(miiout)*1000+conv_integer(suteout)*100+conv_integer(zeciout)*10;
	if(confirmareretragere='1' and retragere='1')then
		sumav:=sumav-sumaintrodusav;	
	end if;
elsif(comanda="11" and sumav>=sfactura)then
	sumav:=sumav-sfactura;
end if;	
if(stop='1')then
	sumav:=0;
	end if;
	suma<=sumav;
	suma_introdusa<=sumaintrodusav;
	
end process; 
end architecture ;