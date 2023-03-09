/*
  23-02-16 영화 상세정보 관람평 css 구현(오병주)
	23-02-18 영화 상세정보 관람평 분리(오병주)
	23-02-23 영화 상세정보 관람평 백엔드 연결(오병주)
*/
import React, { useState, useCallback, useEffect } from 'react';
import { useDispatch, useSelector } from "react-redux";
import { useLocation } from "react-router-dom";
import styled from 'styled-components';
import { StarFilled, UnorderedListOutlined, LikeOutlined, DownOutlined  } from "@ant-design/icons";
import DetailComment from './DetailComment';
import { DETAIL_COMMENT_RECENT_REQUEST, DETAIL_COMMENT_LIKE_REQUEST } from '../../reducer/movie';

const DetailCommentList = () => {
	const location = useLocation();  
	const dispatch = useDispatch();

	// 로그인 리덕스 상태
  const { LOGIN_data } = useSelector((state) => state.R_user_login);

  // 로그인 상태에 따라 전체 검색이 다름(관람평 좋아요 표시 때문)
  useEffect(() => {
    dispatch({
      type: DETAIL_COMMENT_RECENT_REQUEST,
      data: {
        pathname: location.pathname,
        uid: LOGIN_data.uid
      }
    });
  }, [LOGIN_data.uid, location.pathname, dispatch]);

	// 현재 페이지 관람평 리덕스 상태
	const { detailComment } = useSelector((state) => state.movie);

	// 관람평 더보기 limit
	const [limit, setlimit] = useState(10);

	// 정렬 버튼 css 변수
	const [newbutton, setnewbutton] = useState(true);
	const [likebutton, setlikebutton] = useState(false);

	// 최신순 버튼을 누를 때
	const clicknew = useCallback(()=> {
		dispatch({
      type: DETAIL_COMMENT_RECENT_REQUEST,
      data: {
        pathname: location.pathname,
        uid: LOGIN_data.uid
      }
    });
		setnewbutton(true);
		setlikebutton(false);
		setlimit(10);
	}, [LOGIN_data.uid, location.pathname, dispatch])

	// 공감순 버튼을 누를 때
	const clicklike = useCallback(()=> {
		dispatch({
      type: DETAIL_COMMENT_LIKE_REQUEST,
      data: {
        pathname: location.pathname,
        uid: LOGIN_data.uid
      }
    });
		setlikebutton(true);
		setnewbutton(false);
		setlimit(10);
	}, [LOGIN_data.uid, location.pathname, dispatch])

	// 더보기 버튼을 누를 때
	const onMoreClick = useCallback(() => {
    setlimit(limit + 10);
  }, [limit]);

	return (
		<Layout>
			<CommentSection>
				<h4>
					평점 및 관람평
				</h4>
				<CommentHeader>
					<div className='StarCheck'>
						<span>
							<StarFilled style={{color:"#fea408"}}/> &nbsp;관람객 평점
						</span>
					</div>
					<div className='right'>
						<span className='cnt_num'>
							관람평
							<strong> {detailComment.length}</strong>
							개
						</span>
						<ButtonList>
							<ButtonWrap>
								<button className={"btn" + (newbutton ? " active" : "")} onClick={clicknew}>
									<UnorderedListOutlined/> 최신순
								</button>
							</ButtonWrap>
							<ButtonWrap>
								<button style={{marginLeft: "7px"}} className={"btn" + (likebutton ? " active" : "")} onClick={clicklike}>
									<LikeOutlined/> 공감순
								</button>
							</ButtonWrap>
						</ButtonList>
					</div>
				</CommentHeader>
				<CommentList>
					{/* 관람평 존재 유무에 따라 다른화면 출력 */}
					{detailComment.length !== 0 ? 
					detailComment.slice(0, limit).map((comment, index) => <DetailComment comment={comment} key={index}/>) : 
					<NoElement>
						작성된 관람평이 없습니다.
					</NoElement>
          }		
				</CommentList>
			</CommentSection>
			<SubSection>
				{limit >= detailComment.length ? null : 
          <More onClick={onMoreClick}>
            더 보기 <DownOutlined />
          </More>
        }
			</SubSection>
		</Layout>
	);
};

const Layout = styled.div`
	overflow: hidden;
	position: relative;
	width: 100%;
	margin: 0;
	padding: 0;
	border: 0;
	vertical-align: baseline;
	word-break: break-all;
	margin-top: 20px;
`;

const CommentSection = styled.div`
	background: #f8f8f8;
	margin: 0 auto;
	width: 1050px;
	margin-top: 8px;
	background-color: #fff;
	box-shadow: 0px 1px 4px 0px rgb(0 0 0 / 15%);

	h4 {
		padding-top: 18px;
    font-size: 20px;
    text-align: center;
		margin: 0;
	}
`;

const CommentHeader = styled.div`
	position: relative;
	padding: 16px;
	display: flex;
	justify-content: space-between;

	.StarCheck {
		color: #999;
		font-size: 14px;
		margin-left: 14px;
		padding-top: 5px;
	}

	.right {
		display: flex;
    align-items: center;
    margin-left: auto;

		.cnt_num {
			font-size: 14px;
		}
	}
`;

const ButtonList = styled.ul`
	position: relative;
	margin-left: 5px !important;
	list-style: none;
	margin: 0;
	padding: 0;
	margin-right: 11px;
	margin-top: 1px;

	::before{
		content: '';
    display: block;
    position: absolute;
    left: 5px;
    top: 2px;
    width: 1px;
    height: 16px;
    background-color: #ccc;
	}

	li:first-child {
		margin-left: 15px;
	}

`;

const ButtonWrap = styled.li`
	margin-left: 10px;
	list-style: none;
	display: list-item;
	float: left;

	.btn {
		content: "";
		cursor: pointer;
		background-color: white;
		display: block;
		position: relative;
		color: #999;
		font-size: 14px;
		border: 0;
		padding: 0;

		&.active {
      color: #000;
    }
	}
`;

const CommentList = styled.ul`
	padding: 0px 25px 0px 25px;
	background: #f8f8f8;
	margin: 0 auto;
	margin-bottom: 8px;
	background-color: #fff;

	li:first-child {
		border-color: #ccc;
	}
`;

const NoElement = styled.li`
	position: relative;
	border-top: 1px solid #eee;
	list-style: none;
	padding: 140px 0;
	text-align: center;
	font-size: 15px;
`;

const SubSection = styled.div`
	background: #f8f8f8;
	margin: 0 auto;
	width: 1050px;
	margin-top: 35px;
	margin-bottom: 25px;
	background-color: #fff;
`;

const More = styled.button`
	margin-top: -10px !important;
  width: 1050px;
  height: 40px;
  background-color: transparent;
  border: 1px solid #cccccc;
  color: #666;
  cursor: pointer;
  font-size: 1em;
  line-height: 1.15;
  &:hover {
    border: 1px solid black;
  }
`;

export default DetailCommentList;