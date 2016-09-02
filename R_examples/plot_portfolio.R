#data from
#Eric Zivot (2008), Computing Efficient Portfolios in R, http://faculty.washington.edu/ezivot/econ424/portfoliofunctions.pdf
mu.vec <- c(0.0427, 0.0015, 0.0285) 
sigma.mat <- matrix(c(0.0100, 0.0018, 0.0011,0.0018, 0.0109, 0.0026,0.0011, 0.0026, 0.0199), nrow=3, ncol=3)

x.vec <- as.matrix(read.csv("simplex_n3.csv",header=F,sep=";"))
mu.p.x <- x.vec%*%mu.vec
sig2.p.x<-rowSums(x.vec%*%sigma.mat*x.vec)
sig.p.x = sqrt(sig2.p.x)
plot(sig.p.x,mu.p.x,xlab="standard deviation",ylab="expected return",col="gray",pch=20,cex=0.7)
mv<-cbind(sig.p.x,mu.p.x)
chull_points<-mv[chull(mv),]
e.f.points.position<-which(rowSums(sign(diff(chull_points)))==2)
e.f.points.position<-c(e.f.points.position,e.f.points.position[length(e.f.points.position)]+1)
e.f.coordinates<-chull_points[e.f.points.position,]
lines(e.f.coordinates,type="l",lwd=4)
