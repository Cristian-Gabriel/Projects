library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use std.standard.all;
entity cardpin is
	port( card_pin: in std_logic_vector (4 downto 0);
	ok_card:out std_logic);
end cardpin;
architecture verificare of cardpin is 
type rom is array (0 to 3) of std_logic_vector (4 downto 0);
signal rom1: rom:=("00000","01101","10010","11001");
begin
process(card_pin)
variable ok_cardvar: std_logic:='0'; 
begin
if card_pin=rom1(0) then
ok_cardvar:='1';   
elsif card_pin=rom1(1) then
ok_cardvar:='1';   
elsif card_pin=rom1(2) then
ok_cardvar:='1';  
elsif card_pin=rom1(3) then
ok_cardvar:='1';
else
ok_cardvar:='0';
end if;		
ok_card<=ok_cardvar; 

end process;
end architecture;