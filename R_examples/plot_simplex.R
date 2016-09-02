save_pdf<-F
simplex<-as.matrix(read.csv("simplex.csv",sep=";",header=FALSE))
x<-simplex[,2]+simplex[,3]/2
y<-sqrt(3)/2*simplex[,3]

if(save_pdf){
	pdf("fig1.pdf",width=5,height=5)
}else{
par(mfrow=c(2,2))
}
plot(c(0,1),c(0,0),type="l",mar=c(0,0,0,0),ylim=c(0,0.85),xlab="",ylab="",xaxt="n",yaxt="n",bty="n")
lines(c(0,0.5),c(0,sqrt(3)/2))
lines(c(1,0.5),c(0,sqrt(3)/2))
points(x,y,pch=16)
if(save_pdf){
dev.off()
}

selpoint<-simplex[25,]
dsimp<-(simplex-1/3)/((1-1/3)*5)
paround<-t(t(dsimp)+selpoint)
x1<-paround[,2]+paround[,3]/2
y1<-sqrt(3)/2*paround[,3]

if(save_pdf){
pdf("fig2.pdf",width=5,height=5)
}
plot(c(0,1),c(0,0),type="l",mar=c(0,0,0,0),ylim=c(0,0.85),xlab="",ylab="",xaxt="n",yaxt="n",bty="n")
lines(c(0,0.5),c(0,sqrt(3)/2))
lines(c(1,0.5),c(0,sqrt(3)/2))
points(x,y,pch=16,col="gray50")
points(x[25],y[25],pch=16)
points(x1,y1,pch=16,cex=0.5)
if(save_pdf){
dev.off()
}

