   /* Andrew Whiting */
   /*     CS337-2    */
   /*     12/6/13    */
   
var bids =    new Array();
var bidders = new Array();
var bidTime = new Array();

var auctionEnd = new Date();

var auctionActive = true;

auctionEnd.setMinutes(auctionEnd.getMinutes() + 2);
   
function main()
{
   var timeLeft;
   
   timeLeft = setInterval('bidTimer()', 1000);
   /*setTimeout('closeBid()', 120000);*/
}
function writeBid()
{
   var historyText = "";
	  
   for(var index in bids)
      historyText += bidTime[index] + bids[index] + " (" + bidders[index] + ") \n";
		 
   document.bidForm.bidList.value   = historyText;
   document.bidForm.bidId.value     = "";
   document.bidForm.bidAmount.value = "";	  
}
function addBid()
{
   var now = new Date();
   var hours;
   var minutes; 
   var seconds;
   var timeText;
	 
   bidders.unshift(document.bidForm.bidId.value);
   bids.unshift   (document.bidForm.bidAmount.value);
   if(auctionActive)
   {
      hours    = now.getHours  ();
	  minutes  = now.getMinutes() < 10 ? "0" + now.getMinutes() : now.getMinutes();
      seconds  = now.getSeconds() < 10 ? "0" + now.getSeconds() : now.getSeconds();
  
      timeText = "[" + hours + ":" + minutes + ":" + seconds + "] ";
  
      bidTime.unshift(timeText);
  
      writeBid();
   }	  
}
function removeBid()
{
   if(auctionActive)
   {
      bidders.shift();
      bidTime.shift();
      bids.shift   ();
      writeBid     ();
   }
}

function bidTimer()
{
   var now = new Date();
   var minutesRemaining;
   var secondsRemaining;
   
   minutesRemaining = (auctionEnd - now) / (1000 * 60);
   secondsRemaining = (minutesRemaining - Math.floor(minutesRemaining)) * 60;
   secondsRemaining = secondsRemaining < 10 ? "0" + secondsRemaining : secondsRemaining;
   
   document.getElementById("auctionDate").innerHTML = now;
   
   document.getElementById("timeRemaining").innerHTML = 
   Math.floor(minutesRemaining) + ":" + Math.floor(secondsRemaining);
   
   if(minutesRemaining < 0)
   {
      document.getElementById("timeRemaining").innerHTML = "Auction is Over!";
	  endAuction();
   }
}

function endAuction()
{
   auctionActive = false;
}
   





