library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_unsigned.all; 
entity scazator is
	port(retragere: in std_logic;
	comanda: in std_logic_vector(1 downto 0);
	miiout,zeciout,suteout: std_logic_vector(3 downto 0);
	suma_introdusa:out integer;
	suma:out integer);
end entity;	



architecture behavioral of scazator is
signal confirmareretragere :std_logic:='0';
begin
	
	process(miiout,zeciout,suteout,comanda,confirmareretragere,retragere)
	variable sumav,sumaintrodusav:integer:=0;
	begin
		if(comanda="01")then
	sumaintrodusav:=conv_integer(miiout)*1000+conv_integer(suteout)*100+conv_integer(zeciout)*10;
	--miiint:=conv_integer(miiout);
--	suteint:=conv_integer(suteout);
--	zeciint:=conv_integer(zeciout);
	if(confirmareretragere='1' and retragere='1')then
		sumav:=sumav-sumaintrodusav;	
		end if;	 
	end if;
	suma<=sumav;
	suma_introdusa<=sumaintrodusav;
	
  -- end if;
end process;
end architecture ;