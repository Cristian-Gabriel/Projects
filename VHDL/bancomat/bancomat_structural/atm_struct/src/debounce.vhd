library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
 
entity VHDL_Code_Debounce is
port (
DATA: in std_logic;
CLK : in std_logic;
OP_DATA : out std_logic);
end VHDL_Code_Debounce ;
architecture Behavioral1 of VHDL_Code_Debounce is
signal OP1, OP2, OP3: std_logic;
begin
process(CLK)
begin
if rising_edge(CLK) then
OP1 <= DATA;
OP2 <= OP1;
OP3 <= OP2;
end if;
end process;
OP_DATA <= OP1 and OP2 and OP3;
 
end Behavioral1;
