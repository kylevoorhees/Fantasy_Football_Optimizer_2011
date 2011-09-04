#!/usr/bin/perl

# Fantasy Parser
# ESPN

use strict;

my ($infile) = $ARGV[0];
my ($outfile) = $ARGV[1];

print "Reading in file: $infile\n";

open(INFILE, $infile);
open(OUTFILE, ">>$outfile");

while(<INFILE>){

	# See if the line matches Player Info
	if (/.*<a.*>(.*?)<\/a>,\s*(.*?)\s*<\/td><td>\s*(.*?)\s*<\/td><td>\s*(.*?)\s*<\/td><td>\s*(.*?)\s*<\/td>/){
		print "Match found\n";
		print OUTFILE "<player>\n";
		print OUTFILE "\t<name>$1</name>\n";
		print OUTFILE "\t<team>$2</team>\n";
		print OUTFILE "\t<bye>$3</bye>\n";
		print OUTFILE "\t<pos_rank>$4</pos_rank>\n";
		print OUTFILE "\t<cost>$5</cost>\n";
		print OUTFILE "</player>\n";
	}
	
	if (/.*>(.*?)<\/a>,\s*(.*?)\s*<\/nobr>.*2011 Projections.*playertableStat.*?>\s*(.*?)\s*</){
		print "Match found\n";
		print OUTFILE "<player>\n";
		print OUTFILE "\t<name>$1</name>\n";
		print OUTFILE "\t<team>$2</team>\n";
		print OUTFILE "\t<points>$3</points>\n";
		print OUTFILE "</player>\n";
	}
}

1;
