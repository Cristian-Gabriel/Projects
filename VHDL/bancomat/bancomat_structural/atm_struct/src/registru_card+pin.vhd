library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_unsigned.all;

entity reg_univ1 is
	port( 
	d_in : std_logic_vector(4 downto 0);
	clk:in std_logic;
	q : out std_logic_vector(4 downto 0));
end entity; 

architecture arh_reguniv of reg_univ1 is
signal q1 :std_logic_vector(4 downto 0) := "00000";
begin
			  q1<=d_in; 
q<= q1;
end architecture;