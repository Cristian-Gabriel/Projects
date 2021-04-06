library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use std.standard.all;
entity sumatorbancnote is 
	port( confirmarebani,confirmareretragere:in std_logic;
	retragere:in std_logic;
	comanda: in std_logic_vector(1 downto 0);
	miiout,zeciout,suteout: std_logic_vector(3 downto 0);
	euro10,euro100,euro1000:out integer);
	--euro10out,euro100out,euro1000out:out integer);
end sumatorbancnote;
architecture behavioral of sumatorbancnote is  
--signal confirmarebani,confirmareretragere:std_logic:='0';
begin
	process(miiout,zeciout,suteout,comanda,confirmarebani,confirmareretragere)
variable miiint:integer;--:=conv_integer(miiout)*1000;
variable suteint:integer;--:=conv_integer(suteout)*100;
variable zeciint:integer;--:=conv_integer(zeciout)*10;
variable sumav,s,sumab,suma_introdusav:integer:=0;
variable euro10v,euro100v,euro1000v:integer:=0;
begin	
if(comanda="10")then
	
	if(confirmarebani='1')then
		--sumav:=sumav+sumaintrodusav;
		euro10v:=euro10v+conv_integer(zeciout);
		euro100v:=euro100v+conv_integer(suteout);
		euro1000v:=euro1000v+conv_integer(miiout);
	end if;
	elsif(comanda="01")then		
		suma_introdusav:=conv_integer(miiout)*1000+conv_integer(suteout)*100+conv_integer(zeciout)*10;
		sumab:=euro10v*10+euro100v*100+euro1000v*1000;
		if(confirmareretragere='1' and retragere='1') then
				if(suma_introdusav<=sumab)then
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
				end if;
				end if;
				end if;
	end if;
	euro10<=euro10v;
    euro100<=euro100v;
   euro1000<=euro1000v;
  -- end if;
end process;
end architecture ;