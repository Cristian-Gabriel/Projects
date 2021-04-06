library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_unsigned.all;

entity reg_suma is
	port( 
	d_in : in integer; --range;-- 0 to 30;
	clk:in std_logic;
	q : out integer);-- range; --0 to 30);
end entity; 

architecture arh_regsuma of reg_suma is
signal q1 : integer;
begin					 
	process(d_in)
	begin
	  
		q1<=d_in;
		
end process;
q<= q1;
end architecture;