library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use std.standard.all;
entity bancomat is	
port(ok: in std_logic;
confirmarebani, confirmareretragere: in std_logic;
suma_introdusa: out integer;
suma: out integer; 
suma_invalida1:out std_logic:='0';
euro10,euro100,euro1000:in integer;
euro10out,euro100out,euro1000out:out integer;
comanda: in std_logic_vector (1 downto 0);
miiout,zeciout,suteout: in std_logic_vector(3 downto 0);
enablenum:in std_logic
);
end bancomat;

architecture Behavioral of bancomat is

signal stop:std_logic:='0';
begin

process(miiout,suteout,zeciout,comanda,confirmarebani,confirmareretragere,stop)
variable factura:integer:=30;
variable euro100v:integer:=0;
variable euro1000v:integer:=0;
variable euro10v:integer:=0;
type dmux is array (0 to 3) of std_logic_vector (1 downto 0);
variable B_dmux: dmux:=("00","01","10","11");
variable suteint: integer :=0;
variable zeciint: integer :=0;
variable miiint: integer :=0;				 
variable suma_introdusav : integer:=0;						 
variable sumav,sumab,s: integer :=0; 
variable suma_invalida:std_logic:='0';
begin
if(ok='1') then
if(comanda=B_dmux(2))then

miiint:=(conv_integer(miiout)*1000);
suteint:=(conv_integer(suteout)*100);
zeciint:=(conv_integer(zeciout)*10);
suma_introdusav:=suteint+zeciint+miiint;

if(confirmarebani='1')then
sumav:=sumav+(suma_introdusav);
euro10v:=conv_integer(zeciout)+euro10v;
euro100v:=conv_integer(suteout)+euro100v;
euro1000v:=conv_integer(miiout)+euro1000v;

end if;

elsif(comanda=B_dmux(3))then

if(factura<sumav) then
sumav:=sumav-factura;
end if;
elsif(comanda=B_dmux(1))then

miiint:=(conv_integer(miiout)*1000);
suteint:=(conv_integer(suteout)*100);
zeciint:=(conv_integer(zeciout)*10);
suma_introdusav:=miiint+zeciint+suteint;
sumab:=euro10v*10+euro100v*100+euro1000v*1000;
		if(confirmareretragere='1') then
				if((suma_introdusav<=sumav)and(suma_introdusav<=sumab))then
				sumav:=sumav-(miiint+zeciint+suteint);	
				s:=suma_introdusav;
				if(euro1000v>=s/1000)then
					euro1000v:=euro1000v-s/1000;
					s:=s mod 1000;
				elsif(euro1000v<s/1000)then
					s:=s-euro1000v*1000;
					euro1000v:=0;
				end if;
				
				if(euro100v>=s/100)then
					euro100v:=euro100v-s/100;
					s:=s mod 100;
				elsif(euro100v<s/100)then
					s:=s-euro100v*100;
					euro100v:=0;
				end if;
				
				if(euro10v>=s/10)then
					euro10v:=euro10v-s/10;
					s:=s mod 10;
					suma_invalida:='0';
				else
					suma_invalida:='1';
				end if;
				else
					suma_invalida:='1';
				end if;

end if;

end if;
elsif(comanda=B_dmux(0))then
	sumav:=sumav;
	end if;
if(stop='1')then
	sumav:=0;
end if;
if(sumav>=0)then
suma_introdusa<=suma_introdusav;
suma<=sumav; 
euro10out<=euro10v;
euro100out<=euro100v;
euro1000out<=euro1000v;
suma_invalida1<=suma_invalida;
end if;
end process;
end architecture;