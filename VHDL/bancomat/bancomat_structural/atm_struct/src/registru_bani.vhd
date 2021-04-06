library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_unsigned.all;

entity reg_univ is
	port( 
	d_in : in integer; --range;-- 0 to 30;
	clk:in std_logic;
	q : out integer);-- range; --0 to 30);
end entity; 

architecture arh_reguniv11 of reg_univ is
signal q1 : integer:=0;
begin
			  q1<=d_in; 
q<= q1;
end architecture;