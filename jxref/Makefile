# $Id: Makefile,v 1.5 2015-01-13 17:36:10-08 - - $

JAVASRC    = jxref.java listmap.java intqueue.java misc.java
SOURCES    = ${JAVASRC} Makefile README
ALLSOURCES = ${SOURCES}
MAINCLASS  = jxref
CLASSES    = ${JAVASRC:.java=.class}
JARCLASSES = ${CLASSES} intqueue\$$*.class listmap\$$*.class
JARFILE    = jxref
LISTING    = Listing.ps
SUBMITDIR  = cmps012b-wm.w15 asg2

all : ${JARFILE}

${JARFILE} : ${CLASSES}
	echo Main-class: ${MAINCLASS} >Manifest
	jar cvfm ${JARFILE} Manifest ${JARCLASSES}
	- rm -vf Manifest
	chmod +x ${JARFILE}

%.class : %.java
	- checksource $<
	- cid + $<
	javac $<

clean :
	- rm -vf ${JARCLASSES} Manifest

spotless : clean
	- rm -vf ${JARFILE} ${LISTING} ${LISTING:.ps=.pdf}

ci : ${ALLSOURCES}
	- checksource ${ALLSOURCES}
	cid + ${ALLSOURCES}

lis : ${ALLSOURCES}
	mkpspdf -s12 ${LISTING} ${ALLSOURCES}

submit : ${SOURCES}
	submit ${SUBMITDIR} ${SOURCES}

again : ${ALLSOURCES}
	gmake --no-print-directory spotless ci all lis
	
